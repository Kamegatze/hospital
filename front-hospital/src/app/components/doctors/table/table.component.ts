import {Component, OnInit} from '@angular/core';
import {map, Observable, of, Subscription} from "rxjs";
import {Doctor} from "../../../models/doctor";
import {DoctorsService} from "../../../services/doctors.service";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.scss']
})
export class TableComponent implements OnInit{
  protected doctors$!:Observable<Doctor[]>


  ngOnInit(): void {
    this.doctors$ = this.doctorsService.getAll();
  }

  constructor(
    private doctorsService:DoctorsService,
  ) {}

  protected remove(id:number) : void {
    this.doctorsService.removeById(id);
    this.doctors$.pipe(
      map(
        doctors =>
          doctors.filter(doctor => doctor.id !== id)),
    ).subscribe((value) => this.doctors$ = of<Doctor[]>(value));
  }
}
