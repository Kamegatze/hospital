import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable, Subscription} from "rxjs";
import {Doctor} from "../../../models/doctor";
import {DoctorsService} from "../../../services/doctors.service";
import {ActivatedRoute, Router} from "@angular/router";
import {PatientsService} from "../../../services/patients.service";
import {Patient} from "../../../models/patient";
import {FormControl, FormGroup} from "@angular/forms";
import {PatientTransfer} from "../../../models/patient-transfer";

@Component({
  selector: 'app-addtion-reception',
  templateUrl: './addition-reception.component.html',
  styleUrls: ['./addition-reception.component.scss']
})
export class AdditionReceptionComponent implements OnInit, OnDestroy{
  protected doctors$!:Observable<Doctor[]>;
  protected id!:number;
  private routeSubscription!:Subscription;

  constructor(
    private doctorsService:DoctorsService,
    private route:ActivatedRoute,
    private patientService:PatientsService,
    private router:Router,
  ) {
  }

  ngOnInit(): void {
    this.doctors$ = this.doctorsService.getAll();
    this.routeSubscription = this.route.params
      .subscribe(params => this.id = params?.["id"]);
  }

  ngOnDestroy(): void {
    this.routeSubscription?.unsubscribe();
  }

  protected pushReception(id:number) {

    this.patientService.additionReception(id, this.id);

    this.router.navigate([`/patients/under-table/${this.id}`]);
  }
}
