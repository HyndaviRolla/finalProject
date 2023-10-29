import { Component } from '@angular/core';

import { Incident } from '../incident';
import { ActivatedRoute } from '@angular/router';
import { IncidentService } from '../incident.service';
import { AuthService } from '../auth.service';
import { ForwardingRequest } from '../forwadingrequest';
@Component({
  selector: 'app-assigned-tickets',
  templateUrl: './assigned-tickets.component.html',
  styleUrls: ['./assigned-tickets.component.css']
})
export class AssignedTicketsComponent {
  
  private currentUser: string | null;

  incidents: Incident[] = [];

  successMessage: string | null = null;
  incidentId!: number;
  availableGroups: string[] = [];
  selectedGroup: string | null = null;
  forwardingMessage: string = '';
  
  constructor(
    private route: ActivatedRoute, private incidentService: IncidentService, private authService: AuthService) { 
    
    this.currentUser = this.authService.getCurrentUser(); 
    }
    
  ngOnInit() { 
    this.getAssignedTickets();
    this.loadAvailableGroups();

  }
  getAssignedTickets() { 
    const currentUser: string | null = this.authService.getCurrentUser();
    if (!currentUser) {
      console.error('No user logged in.');
      return;
    }

      this.incidentService.getAssignedTickets(currentUser)
        .subscribe(
          (incident) => {
            this.incidents = incident; 
    
          },
        );
    }  
 
    updateStatus(incidentId: number) {
      if(incidentId!==null){
      this.incidentService.updateIncidentStatus(incidentId).subscribe(
        () => {
          this.successMessage = 'Incident status updated successfully to InProgress.';
 
          setTimeout(() => {
            this.successMessage = null;
          }, 5000);
          console.log('Incident status updated successfully.'); 
          this.getAssignedTickets();
        }, 
      );
    }else {
      console.error('Error: incidentId is null');
    }
  
}
  submitResolution(incident: Incident) {
    if (incident.id !== null) {
      this.incidentService.updateIncidentResolution(incident.id, incident.resolution).subscribe(
        () => {
          this.successMessage = 'Resolution submitted successfully';
 
          setTimeout(() => {
            this.successMessage = null;
          }, 5000);
          console.log('Resolution submitted successfully');
 
        },
      );
    }
  }

  forwardIncident(incident: Incident) {

    if (!this.selectedGroup) {
      console.error('Please select a group to forward the incident.');
      return;
    }

    if (!this.forwardingMessage) {
      console.error('Please enter a forwarding message.');
      return;
    }

    const forwardingRequest: ForwardingRequest = {
      targetGroup: this.selectedGroup,
      message: this.forwardingMessage,
    };
    this.incidentService.forwardIncident(incident.id!, forwardingRequest).subscribe(
      (forwardedIncidents) => {
        this.successMessage = 'Incident  forwarded successfully to ${this.selectedGroup} group';
 
        setTimeout(() => {
          this.successMessage = null;
        }, 5000);
        console.log(
          `Incident forwarded successfully to ${this.selectedGroup} group`
        ); 
      },
    );
  }
  
  private loadAvailableGroups() {
    this.availableGroups = ['SoftwareGroup', 'Connectivity Issues', 'Security Operations Center'];
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
