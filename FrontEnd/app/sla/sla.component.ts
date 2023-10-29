import { Component } from '@angular/core';
import { Incident } from '../incident';
import { IncidentService } from '../incident.service'; 
import { AuthService } from '../auth.service';
@Component({
  selector: 'app-sla',
  templateUrl: './sla.component.html',
  styleUrls: ['./sla.component.css']
})
export class SlaComponent {
  incidents: Incident[] = []; 
  successMessage:string|null=null;
  constructor(private incidentService: IncidentService,private authService :AuthService) {}
  ngOnInit(): void {
    this.fetchIncidents();
  }

  fetchIncidents(): void { 
    this.incidentService.getIncidentsDueToday()
      .subscribe(incidents => this.incidents = incidents);
  } 

  assignToSelf(incidentId: number | null) {
    const currentUser: string | null = this.authService.getCurrentUser();
    if (!currentUser) {
      console.error('No user logged in.');
      return;
    }

    const currentUserEmail: string | null = this.authService.getCurrentUserEmail();

    if (!currentUserEmail) {
      console.error('No user logged in.');
      return;
    }
    if (incidentId !== null) {
      this.incidentService.assignIncidentToSelf(incidentId,currentUser,currentUserEmail)
        .subscribe(
          updatedIncident => {
            console.log('Incident assigned successfully', updatedIncident);
            this.successMessage = 'Incident assigned successfully';
          setTimeout(() => {
            this.successMessage = null;
          }, 5000);
          },
        );
    } else {
      console.error('Error: incidentId is null');
    }
  }
    
  showDetails(ticketId: number | null) {
    if (ticketId !== null) {
         const sideTab = document.getElementById(`sideTab${ticketId}`);
  
       if (sideTab) { 
            const isVisible = sideTab.style.right === '0px' || !sideTab.style.right;
        sideTab.style.right = isVisible ? '-400px' : '0px';
      } else {
        console.error(`Side tab with ID 'sideTab${ticketId}' not found`);
      }
    } else {
      console.error('Ticket ID is null');
    }
  }
}
