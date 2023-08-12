import {Component, OnInit} from '@angular/core';
import {DoctorTransfer} from "../../../models/doctor-transfer";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Doctor} from "../../../models/doctor";
import {DoctorsService} from "../../../services/doctors.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-addition-data',
  templateUrl: './addition-data.component.html',
  styleUrls: ['./addition-data.component.scss']
})
export class AdditionDataComponent implements OnInit{

  protected doctorForm!:FormGroup;

  ngOnInit(): void {
    this.doctorForm = new FormGroup<DoctorTransfer>(<DoctorTransfer>{
      id: new FormControl<number | null>(null),
      firstName: new FormControl<string>('', [
          Validators.required,
          Validators.minLength(2),
          Validators.pattern(/^[а-яА-я]*$/)
      ]),
      lastName: new FormControl<string>('',[
        Validators.required,
        Validators.minLength(2),
        Validators.pattern(/^[а-яА-я]*$/)
      ]),
      patronymic: new FormControl<string>('', [
        Validators.required,
        Validators.minLength(2),
        Validators.pattern(/^[а-яА-я]*$/)
      ]),
      post: new FormControl<string>('', [
        Validators.required,
        Validators.minLength(4),
        Validators.pattern(/^[а-яА-я\s]*$/)
      ]),
      jobTimeBeginHour: new FormControl<string>('',[
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(2),
        Validators.pattern(/^[0-9]*$/)
      ]),
      jobTimeBeginMinute: new FormControl<string>('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(2),
        Validators.pattern(/^[0-9]*$/)
      ]),
      jobTimeEndHour: new FormControl<string>('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(2),
        Validators.pattern(/^[0-9]*$/)
      ]),
      jobTimeEndMinute: new FormControl<string>('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(2),
        Validators.pattern(/^[0-9]*$/)
      ]),
    });
  }

  constructor(
      private doctorService:DoctorsService,
      private router: Router,
  ) {
  }

  protected correctionJobTimeBegin() {
    return (this.doctorForm.get('jobTimeBeginHour')?.touched &&
        this.doctorForm.get('jobTimeBeginMinute')?.touched) &&
        (this.doctorForm.get('jobTimeBeginHour')?.invalid ||
            this.doctorForm.get('jobTimeBeginMinute')?.invalid);
  }

  protected correctionJobTimeEnd() {
    return (this.doctorForm.get('jobTimeEndHour')?.touched &&
        this.doctorForm.get('jobTimeEndMinute')?.touched) &&
        (this.doctorForm.get('jobTimeEndMinute')?.invalid ||
        this.doctorForm.get('jobTimeEndHour')?.invalid);
  }

  protected submit() {
    const objectDoctor = this.doctorForm?.value;

    const {id, firstName, lastName, patronymic,
      post, jobTimeBeginHour, jobTimeBeginMinute,
      jobTimeEndHour, jobTimeEndMinute} = objectDoctor;

    const doctor:Doctor = {
      id,
      firstName,
      lastName,
      patronymic,
      post,
      jobTimeBegin:`${jobTimeBeginHour}:${jobTimeBeginMinute}`,
      jobTimeEnd: `${jobTimeEndHour}:${jobTimeEndMinute}`,
      patientDTOS: []
    };

    this.doctorService.doctorAddition(doctor);
    this.router.navigate(["/"]);
  }

}
