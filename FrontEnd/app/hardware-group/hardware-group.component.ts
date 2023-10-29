import { Component } from '@angular/core';
import { IncidentService } from '../incident.service';
import { Incident } from '../incident'; 
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-hardware-group',
  templateUrl: './hardware-group.component.html',
  styleUrls: ['./hardware-group.component.css']
})

export class HardwareGroupComponent {

  showIncidentList: boolean = false;
  showForwardedList: boolean = false; 
   
  toggleIncidentList() {
    this.showIncidentList = !this.showIncidentList;
  }

  toggleForwardedList() {
    this.showForwardedList = !this.showForwardedList;
  }
  buttonClicked = true;

 
  private currentUser: string | null;
  private currentUserEmail: string | null;
  incidents: Incident[] = [];
  forwardedIncidents: Incident[] = [];
  availableGroups: string[] = [];
  selectedGroup: string | null = null;
  forwardingMessage: string = '';
  highPriorityHighSeverityIncidents: Incident[] = [];
  
  constructor(
    private route: ActivatedRoute,  private router:Router,private incidentService: IncidentService, private authService: AuthService) {
   
    this.currentUser = this.authService.getCurrentUser();
    this.currentUserEmail = this.authService.getCurrentUserEmail();
    }
  
  incidentCount: number = 0;
  forwardedIncidentCount: number = 0; 
  incidentId!: number;
  successMessage: string | null = null;
  idParam!: number;

 
  ngOnInit() {
    this.fetchIncidents('HardwareGroup');
    this.fetchForwardedIncidents('HardwareGroup');
   
  
  }

  fetchIncidents(group: string) {
    this.incidentService.getIncidentsByAssignmentGroup(group).subscribe(
      (incidents) => {
        this.incidents = incidents;
        this.incidentCount = incidents.length;

      },
    );
  }
  fetchForwardedIncidents(group: string) {
    this.incidentService.getForwardedIncidents(group).subscribe(
      (forwardedIncidents) => {
        this.forwardedIncidents = forwardedIncidents;
        this.forwardedIncidentCount = forwardedIncidents.length;

      },
    );
  }

  assignToSelf(incidentId: number | null) {
    if (incidentId === null) {
      console.error('Error: incidentId is null');
      return;
    }

    const currentUser: string | null = this.authService.getCurrentUser();
    const currentUserEmail: string | null = this.authService.getCurrentUserEmail();
    if (!currentUser || !currentUserEmail) {
      console.error('No user logged in.');
      return;
    }

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