import {Component, OnDestroy, OnInit} from '@angular/core';
import {Observable, of, Subscription} from "rxjs";
import {Doctor} from "../../../models/doctor";
import {PatientsService} from "../../../services/patients.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-under-table',
  templateUrl: './under-table.component.html',
  styleUrls: ['./under-table.component.scss']
})
export class UnderTableComponent implements OnInit, OnDestroy{

  protected doctors$!:Observable<Doctor[]>

  private patientSubscription!:Subscription

  private routeSubscription!:Subscription;
  constructor(
    private patientsService: PatientsService,
    private route: ActivatedRoute,
  ) {
  }

  ngOnDestroy(): void {
    this.patientSubscription?.unsubscribe();
    this.routeSubscription?.unsubscribe();
  }

  ngOnInit(): void {

    this.routeSubscription = this.route.params
      .subscribe((params) =>
        this.patientSubscription = this.patientsService.patientById(params?.["id"])
          .subscribe(patient => this.doctors$ = of<Doctor[]>(patient.doctorDTOS))
      );
  }

}
