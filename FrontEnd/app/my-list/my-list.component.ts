import { Component } from '@angular/core';
import { IncidentService } from '../incident.service';
import { AuthService } from '../auth.service';
import { Incident } from '../incident';
import { NetworkElement } from '../network-element';

@Component({
  selector: 'app-my-list',
  templateUrl: './my-list.component.html',
  styleUrls: ['./my-list.component.css']
})
export class MyListComponent {

  myList: Incident[] = []
  constructor(private incidentService: IncidentService, private authService: AuthService) { }


  ngOnInit(): void {
    this.loadIncidents();
  }

  loadIncidents() {
    const currentUser: string | null = this.authService.getCurrentUser();
    const role: string | null = this.authService.getCurrentUserRole();
    console.log(role);
    console.log(currentUser);
    this.incidentService.getIncidentsByUserName(currentUser!)
      .subscribe((data: Incident[]) => {
        this.myList = data;
      });
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