import {Component, OnDestroy, OnInit} from '@angular/core';
import {PatientsService} from "../../../services/patients.service";
import {Observable} from "rxjs";
import {Patient} from "../../../models/patient";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit, OnDestroy{

  protected patients$!:Observable<Patient[]>;

  constructor(
    private patientsService:PatientsService,
  ) {
  }

  ngOnDestroy(): void {

  }

  ngOnInit(): void {
    this.patients$ = this.patientsService.getAll();
  }

}
