import {Component, OnDestroy, OnInit} from '@angular/core';
import {PatientsService} from "../../../services/patients.service";
import {map, Observable, of} from "rxjs";
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

  protected remove(id:number) {
    this.patientsService.removeById(id);
    this.patients$.pipe(
      map(patients => patients.filter(patient => patient.id != id))
    ).subscribe(patients => this.patients$ = of<Patient[]>(patients))
  }

}
