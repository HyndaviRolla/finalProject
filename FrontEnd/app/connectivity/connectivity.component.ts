
import { Component } from '@angular/core';
import { IncidentService } from '../incident.service';
import { Incident } from '../incident';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-connectivity',
  templateUrl: './connectivity.component.html',
  styleUrls: ['./connectivity.component.css']
})

export class ConnectivityComponent {

  showIncidentList: boolean = false;
  showForwardedList: boolean = false;

  toggleIncidentList() {
    this.showIncidentList = !this.showIncidentList;
  }

  toggleForwardedList() {
    this.showForwardedList = !this.showForwardedList;
  }
  buttonClicked = true;

  incidents: Incident[] = [];
  forwardedIncidents: Incident[] = [];

  constructor(private incidentService: IncidentService, private authService: AuthService) { }

  incidentCount: number = 0;
  forwardedIncidentCount: number = 0; 
  successMessage: string | null = null;

  ngOnInit() {
    this.fetchIncidents('Connectivity Issues');
    this.fetchForwardedIncidents('Connectivity Issues');
  }

  fetchForwardedIncidents(group: string) {
    this.incidentService.getForwardedIncidents(group).subscribe(
      (forwardedIncidents) => {
        this.forwardedIncidents = forwardedIncidents;
        this.forwardedIncidentCount = forwardedIncidents.length;
      },
    );
  }
  fetchIncidents(group: string) {
    this.incidentService.getIncidentsByAssignmentGroup(group).subscribe(
      (incidents) => {
        this.incidents = incidents;
        this.incidentCount = incidents.length;

      },
    );
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
      this.incidentService.assignIncidentToSelf(incidentId, currentUser, currentUserEmail)
        .subscribe(
          updatedIncident => {
            
          this.successMessage = 'Incident assigned successfully';
 
          setTimeout(() => {
            this.successMessage = null;
          }, 5000);
            console.log('Incident assigned successfully', updatedIncident);
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