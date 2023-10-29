import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { HardwareGroupComponent } from '../hardware-group/hardware-group.component';
@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  constructor(private authService: AuthService) {}

  ngOnInit(): void {
  }

  
  isHardware(): boolean {
    return this.authService.getCurrentUserRole() === 'HARDWARE_TEAM';
  }
  isConnectivity():boolean
{
  return this.authService.getCurrentUserRole() === 'CONNECTIVITY_TEAM';
}  isUser(): boolean{
    return this.authService.getCurrentUserRole()=='USER';
  }
}
