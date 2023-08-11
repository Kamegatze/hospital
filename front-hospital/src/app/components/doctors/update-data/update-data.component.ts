import {Component, OnDestroy, OnInit} from '@angular/core';
import {DoctorTransfer} from "../../../models/doctor-transfer";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {delay, Observable, Subscription, timeout} from "rxjs";
import {DoctorsService} from "../../../services/doctors.service";
import {Doctor} from "../../../models/doctor";
import {Patient} from "../../../models/patient";

@Component({
  selector: 'app-update-data',
  templateUrl: './update-data.component.html',
  styleUrls: ['./update-data.component.scss']
})
export class UpdateDataComponent implements OnInit, OnDestroy{
  protected doctorForm!:FormGroup;
  private routeSub!:Subscription;

  private doctorById$!:Observable<Doctor>;

  private userSub!:Subscription;
  constructor(
    private route: ActivatedRoute,
    private doctorsService: DoctorsService,
    private router:Router,
  ) {
  }
  ngOnInit(): void {

    let id:number = 0;

    this.routeSub = this.route
      .params
      .subscribe(params => id = params['id']);

    this.doctorById$ = this.doctorsService.doctorById(id);

    this.doctorForm = new FormGroup<DoctorTransfer>(<DoctorTransfer>{
        id: new FormControl<number>(id),
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
        post: new FormControl<string>('', [
            Validators.required,
            Validators.minLength(4),
            Validators.pattern(/^[а-яА-я]*$/)
        ]),
        jobTimeBeginHour: new FormControl<string>('', [
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
        patientDTOS: new FormControl<Patient[]>([])
    });

      this.userSub = this.doctorById$
          .subscribe((doctor) => {
            const [jobTimeBeginHour, jobTimeBeginMinute] = doctor.jobTimeBegin.split(":");
            const [jobTimeEndHour, jobTimeEndMinute] = doctor.jobTimeEnd.split(":");


            this.doctorForm.setValue({
                id: doctor.id,
                firstName: doctor.firstName,
                lastName: doctor.lastName,
                patronymic: doctor.patronymic,
                post: doctor.post,
                jobTimeBeginHour,
                jobTimeBeginMinute,
                jobTimeEndHour,
                jobTimeEndMinute,
                patientDTOS: doctor.patientDTOS
            })
          });

  }

  ngOnDestroy(): void {
    this.routeSub?.unsubscribe();
    this.userSub?.unsubscribe();
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

  protected update() : void {
    const objectDoctor = this.doctorForm?.value;

    const doctor:Doctor = {
        id: Number(objectDoctor.id),
        firstName: String(objectDoctor.firstName),
        lastName: String(objectDoctor.lastName),
        patronymic: String(objectDoctor.patronymic),
        post: String(objectDoctor.post),
        jobTimeBegin: `${objectDoctor.jobTimeBeginHour}:${objectDoctor.jobTimeBeginMinute}:00`,
        jobTimeEnd: `${objectDoctor.jobTimeEndHour}:${objectDoctor.jobTimeEndMinute}:00`,
        patientDTOS: objectDoctor.patientDTOS,
    }

    this.doctorsService.updateUser(doctor);

    this.router.navigate(["/"]);
  }

}
