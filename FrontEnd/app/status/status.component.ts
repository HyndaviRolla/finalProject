import { Component } from '@angular/core';
import { IncidentService } from '../incident.service';
import { Incident } from '../incident';
import { ForwardingRequest } from '../forwadingrequest';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent { 
  incidents: Incident[] = [];
  successMessage:string|null=null;
  selectedStatus: string = 'New';   
 
  constructor(private incidentService: IncidentService,private authService:AuthService) { }

  ngOnInit(): void {
    this.fetchIncidentsByStatus();
  }

  fetchIncidentsByStatus(): void {
    this.incidentService.getIncidentsByStatus(this.selectedStatus)
      .subscribe(incidents => this.incidents = incidents);
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
  statusColor: { [key: string]: string } = {
    'New': 'red',
    'InProgress':'purple',
    'Assigned':'orange',
    'Resolved': 'greenyellow',
    'Forwarded': 'blue',
    'Closed': 'green'
  };
}
