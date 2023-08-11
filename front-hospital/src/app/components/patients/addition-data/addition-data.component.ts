import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PatientTransfer} from "../../../models/patient-transfer";
import {PatientsService} from "../../../services/patients.service";
import {Router} from "@angular/router";
import {Doctor} from "../../../models/doctor";

@Component({
  selector: 'app-addition-data',
  templateUrl: './addition-data.component.html',
  styleUrls: ['./addition-data.component.scss']
})
export class AdditionDataComponent implements OnInit, OnDestroy{
  protected patientForm!:FormGroup;

  constructor(
    private patientsService: PatientsService,
    private router:Router,
  ) {
  }
  ngOnDestroy(): void {
  }

  ngOnInit(): void {
    this.patientForm = new FormGroup<PatientTransfer>(<PatientTransfer>{
      id:new FormControl(null),
      firstName: new FormControl<string>('', [
        Validators.required,
        Validators.minLength(2),
        Validators.pattern(/^[а-яА-я]*$/)
      ]),
      lastName: new FormControl<string>('', [
        Validators.required,
        Validators.minLength(2),
        Validators.pattern(/^[а-яА-я]*$/)
      ]),
      patronymic: new FormControl<string>('', [
        Validators.required,
        Validators.minLength(2),
        Validators.pattern(/^[а-яА-я]*$/)
      ]),
      disease: new FormControl<string>('', [
        Validators.required,
        Validators.minLength(5),
        Validators.pattern(/^[а-яА-я\s]*$/)
      ]),
      age: new FormControl<number>(0, [
        Validators.required,
        Validators.min(0),
        Validators.max(100),
        Validators.pattern(/^[0-9]*$/)
      ]),
      doctorDTOS: new FormControl<Doctor[]>([])
    });
  }

  protected submit() {
    this.patientsService.patientAddition(this.patientForm);
    this.router.navigate(['/patients']);
  }

}
