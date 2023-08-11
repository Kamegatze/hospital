import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {delay, Observable} from "rxjs";
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
    return this.http.get<Patient[]>("http://localhost:8080/patients/")
      .pipe(
        delay(500)
      );
  }

  public patientById(id:number) : Observable<Patient> {
    return this.http.get<Patient>(`http://localhost:8080/patients/${id}`)
      .pipe(
        delay(500)
      );
  }

  public patientAddition(patient:FormGroup<PatientTransfer>) {
    this.http.post("http://localhost:8080/patients/", patient.value)
      .subscribe();
  }

  public removeById(id:number) {
    this.http.delete(`http://localhost:8080/patients/${id}`)
      .subscribe();
  }

  public patientUpdate(patient:FormGroup<PatientTransfer>) {
    this.http.put("http://localhost:8080/patients/", patient.value)
      .subscribe();
  }

  public additionReception(doctorId:number, patientId:number) {
    this.http.get(`http://localhost:8080/patients/addition-reception?doctorId=${doctorId}&patientId=${patientId}`)
      .subscribe();
  }

  cancellationOfReception(doctorId: number, patientId: number) {
    this.http.get(`http://localhost:8080/patients/cancel-of-reception?doctorId=${doctorId}&patientId=${patientId}`)
      .subscribe();
  }
}
