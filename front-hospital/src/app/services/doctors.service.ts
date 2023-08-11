import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {delay, Observable} from "rxjs";
import {Doctor} from "../models/doctor";

@Injectable({
  providedIn: 'root'
})
export class DoctorsService {

  constructor(
    private http:HttpClient
  ) { }

  public getAll(): Observable<Doctor[]> {
    return this.http.get<Doctor[]>("http://localhost:8080/doctors/")
      .pipe(delay(500));
  }

  public removeById(id:number) {
    this.http.delete(`http://localhost:8080/doctors/${id}`)
      .subscribe();
  }

  public updateUser(doctor:Doctor) {
    this.http.put("http://localhost:8080/doctors/", doctor)
        .subscribe();
  }

  public doctorById(id:number) : Observable<Doctor> {
    return this.http.get<Doctor>(`http://localhost:8080/doctors/${id}`)
      .pipe(
        delay(500)
      );
  }

  public doctorAddition(doctor: Doctor) {
    this.http.post("http://localhost:8080/doctors/", doctor)
        .subscribe();
  }
}
