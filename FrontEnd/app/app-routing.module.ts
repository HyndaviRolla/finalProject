import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NetworkElementComponent } from './network-element/network-element.component';
import { HardwareGroupComponent } from './hardware-group/hardware-group.component'; 
import { LoginComponent } from './login/login.component';
import { IncidentComponent } from './incident/incident.component';
import { MyListComponent } from './my-list/my-list.component';
import { ConnectivityComponent } from './connectivity/connectivity.component';
import { StatusComponent } from './status/status.component';
import { SlaComponent } from './sla/sla.component';
import { SearchComponent } from './search/search.component';
import { AssignedTicketsComponent } from './assigned-tickets/assigned-tickets.component';

const routes: Routes = [
  
  
  { path: 'network', component: NetworkElementComponent },
  { path: 'hardware', component: HardwareGroupComponent }, 
  { path: 'login', component: LoginComponent },
  { path: 'incident', component: IncidentComponent },
  { path: 'myList', component: MyListComponent},
  { path: 'connectivity',component: ConnectivityComponent },
  { path: 'status',component:StatusComponent},
  { path: 'dueToday',component:SlaComponent},
  {path:'Search',component:SearchComponent},
  {path:'Ticketsme',component:AssignedTicketsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers:[]
})
export class AppRoutingModule { }
