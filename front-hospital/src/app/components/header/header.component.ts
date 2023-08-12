import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  constructor(
    private router:Router,
  ) {}

  protected isDoctors() {
    return this.router.url.includes("/doctors");
  }

  protected isPatients() {
    return this.router.url.includes("/patients");
  }
}
