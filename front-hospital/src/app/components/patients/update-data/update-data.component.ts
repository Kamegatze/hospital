import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Subscription} from "rxjs";
import {PatientsService} from "../../../services/patients.service";
import {ActivatedRoute, Router} from "@angular/router";
import {PatientTransfer} from "../../../models/patient-transfer";
import {Patient} from "../../../models/patient";
import {Doctor} from "../../../models/doctor";

@Component({
  selector: 'app-update-data',
  templateUrl: './update-data.component.html',
  styleUrls: ['./update-data.component.scss']
})
export class UpdateDataComponent implements OnInit, OnDestroy{
  protected patientForm!:FormGroup;

  private routeSubscription!:Subscription;
  private patientSubscription!:Subscription;

  constructor(
    private patientService: PatientsService,
    private route:ActivatedRoute,
    private router:Router,
  ) {
  }

  ngOnDestroy(): void {
    this.routeSubscription?.unsubscribe();
    this.patientSubscription?.unsubscribe();
  }

  ngOnInit(): void {
    this.patientForm = new FormGroup<PatientTransfer>(<PatientTransfer>{
      id:new FormControl(),
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

    this.routeSubscription = this.route.params
      .subscribe(params => {
        this.patientSubscription = this.patientService.patientById(params?.["id"])
          .subscribe(patient => {
            const {id, firstName, lastName,
              patronymic, disease, age, doctorDTOS} = patient;
            this.patientForm.setValue(<Patient>{
              id,
              firstName,
              lastName,
              patronymic,
              disease,
              age,
              doctorDTOS
            });
          })
      });
  }

  protected submit() {
    this.patientService.patientUpdate(this.patientForm);
    this.router.navigate(['/patients']);
  }
}
