import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Patient} from "../models/patient";

@Injectable({
  providedIn: 'root'
})
export class PatientsService {

  constructor(
    private http:HttpClient,
  ) { }

  public getAll() : Observable<Patient[]> {
    return this.http.get<Patient[]>("http://localhost:8080/patients/");
  }

  public patientBuId(id:number) : Observable<Patient> {
    return this.http.get<Patient>(`http://localhost:8080/patients/${id}`)
  }

}
