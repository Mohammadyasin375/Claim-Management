import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Plan } from 'src/app/model/plan.model';
import { AddplanService } from 'src/app/service/plan.service';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-add-plan',
  templateUrl: './add-plan.component.html',
  styleUrls: ['./add-plan.component.css']
})
export class AddPlanComponent implements OnInit {

 
  planType:string[];
  plan:Plan;
  addPlanForm: FormGroup;
  msg: string;

  constructor(private authService: AuthService,private addPlanService:AddplanService) { }

  ngOnInit(): void {

    this.planType= ['TermLife','Critical','FixedBenefits'];

    this.addPlanForm = new FormGroup({
      planType: new FormControl('', [Validators.required]),
      startDate: new FormControl('', [Validators.required]),
      endDate: new FormControl('', [Validators.required]),
      insuredAmount: new FormControl('', [Validators.required])
    });

  }

  onSubmitPlan(){

    this.plan = {
      planType: this.addPlanForm.value.planType,
      insuredAmount: this.addPlanForm.value.insuredAmount,
      startDate: this.addPlanForm.value.startDate,
      endDate: this.addPlanForm.value.endDate
    };


    let token = localStorage.getItem('token');
    this.addPlanService.addPlan(token,this.plan).subscribe({
      next: (data)=>{
        this.authService.msg$.next('Plan Submitted!!')
      },
      error: (err)=>{
        
      }
    });
  }

}
