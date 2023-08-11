import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable, of, Subscription} from "rxjs";
import {Patient} from "../../../models/patient";
import {DoctorsService} from "../../../services/doctors.service";
import {ActivatedRoute, Route} from "@angular/router";

@Component({
  selector: 'app-under-table',
  templateUrl: './under-table.component.html',
  styleUrls: ['./under-table.component.scss']
})
export class UnderTableComponent implements OnInit, OnDestroy{
  protected patients$!:Observable<Patient[] | null>;
  private routeSubscription!:Subscription;
  private doctorSubscription!:Subscription;

  constructor(
      private doctorsService: DoctorsService,
      private route: ActivatedRoute,
  ) {
  }

  ngOnDestroy(): void {
    this.routeSubscription?.unsubscribe();
    this.doctorSubscription?.unsubscribe();
  }

  ngOnInit(): void {
    let id:number = 0;

    this.routeSubscription = this.route.params
        .subscribe((params) => id = params?.["id"])

    this.doctorSubscription = this.doctorsService.doctorById(id)
        .subscribe(doctor => this.patients$ = of<Patient[] | null>(doctor.patientDTOS))
  }

}
