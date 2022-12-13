import { componentFactoryName } from '@angular/compiler';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { SignUpComponent } from './auth/sign-up/sign-up.component';
import { AdminComponent } from './components/admin/admin.component';
import { ViewClaimsComponent } from './components/admin/view-claims/view-claims.component';
import { AddPlanComponent } from './components/member/add-plan/add-plan.component';
import { ApplyClaimComponent } from './components/member/apply-claim/apply-claim.component';
import { MemberComponent } from './components/member/member.component';
import { ProfileComponent } from './components/member/profile/profile.component';

const routes: Routes = [
  {path:'',component:LoginComponent},
  {path:'sign-up',component:SignUpComponent},
  {path:'member',component:MemberComponent,children:[
    {path:'profile',component:ProfileComponent},
    {path:'add-plan',component:AddPlanComponent},
    {path:'apply-claim',component:ApplyClaimComponent},
    {path:'view-plans',component:ViewClaimsComponent}
  ]},
  {path:'admin',component:AdminComponent,children:[
    {path:'profile',component:ProfileComponent},
    {path:'view-claims',component:ViewClaimsComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
