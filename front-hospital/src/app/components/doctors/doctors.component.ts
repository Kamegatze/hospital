import {Component, OnInit} from '@angular/core';
import {DoctorsService} from "../../services/doctors.service";
import {map, Observable, of, Subscription} from "rxjs";
import {Doctor} from "../../models/doctor";
import {Router} from "@angular/router";

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.scss']
})
export class DoctorsComponent{

}
