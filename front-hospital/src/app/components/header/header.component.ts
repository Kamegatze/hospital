import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  protected doctorActive:boolean = true;
  protected patientActive:boolean = false;

  protected changeDoctor() {
    this.doctorActive = !this.doctorActive;
    this.patientActive = !this.patientActive;
  }

  protected changePatient() {
    this.patientActive = !this.patientActive;
    this.doctorActive = !this.doctorActive;
  }
}
