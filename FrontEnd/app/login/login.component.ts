 
  
import { Component } from '@angular/core';

import { AuthService } from '../auth.service';
 

import { Router } from '@angular/router';

 

@Component({

  selector: 'app-login',

  templateUrl: './login.component.html',

  styleUrls: ['./login.component.css']

})

export class LoginComponent {

  username: string = '';

  password: string = '';

  error: string = '';

  constructor(private authService: AuthService, private router: Router) {}
 
  login() {

    this.authService.login(this.username, this.password).subscribe(

      () => {
        if ( this.authService.getCurrentUserRole()==='HARDWARE_TEAM'||this.authService.getCurrentUserRole()==='CONNECTIVITY_TEAM') {
          console.log(this.authService.getCurrentUserRole()==='HARWARE_TEAM');
           this.router.navigate(['/Ticketsme']);
        } else if (this.authService.getCurrentUserRole()==='USER') {
          this.router.navigate(['/myList']);
        }  
          

      },

      (error) => {

        console.error('Authentication error:', error);

        if (error.status === 401) {

          this.error = 'Invalid Credentials';

        } else {

          this.error = 'An error occurred during authentication.';

        }

      }

    );

  }

 

}