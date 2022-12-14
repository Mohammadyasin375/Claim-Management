import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { SignUpComponent } from './auth/sign-up/sign-up.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { NavbarComponent } from './core/navbar/navbar.component';
import { MemberComponent } from './components/member/member.component';
import { ProfileComponent } from './components/member/profile/profile.component';
import { ApplyClaimComponent } from './components/member/apply-claim/apply-claim.component';
import { ViewPlansComponent } from './components/member/view-plans/view-plans.component';
import { AddPlanComponent } from './components/member/add-plan/add-plan.component';
import { AdminComponent } from './components/admin/admin.component';
import { ViewClaimsComponent } from './components/admin/view-claims/view-claims.component';
import { AllClaimsComponent } from './components/member/all-claims/all-claims.component';
import { AllMembersComponent } from './components/admin/all-members/all-members.component';
import { ApproveClaimComponent } from './components/admin/approve-claim/approve-claim.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignUpComponent,
    NavbarComponent,
    MemberComponent,
    ProfileComponent,
    ApplyClaimComponent,
    ViewPlansComponent,
    AddPlanComponent,
    AdminComponent,
    ViewClaimsComponent,
    AllClaimsComponent,
    AllMembersComponent,
    ApproveClaimComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
