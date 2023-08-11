import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Patient} from "../models/patient";
import {PatientTransfer} from "../models/patient-transfer";
import {FormGroup} from "@angular/forms";

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

  public patientById(id:number) : Observable<Patient> {
    return this.http.get<Patient>(`http://localhost:8080/patients/${id}`)
  }

  public patientAddition(patient:FormGroup<PatientTransfer>) {
    this.http.post("http://localhost:8080/patients/", patient.value)
      .subscribe();
  }
}
