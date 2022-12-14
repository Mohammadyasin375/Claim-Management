import { Component, OnInit } from '@angular/core';
import { Plan } from 'src/app/model/plan.model';
import { ViewplansService } from 'src/app/service/viewplans.service';

@Component({
  selector: 'app-view-plans',
  templateUrl: './view-plans.component.html',
  styleUrls: ['./view-plans.component.css']
})
export class ViewPlansComponent implements OnInit {

  plan:Plan[];
  constructor(private viewPlansService:ViewplansService) { }

  ngOnInit(): void {
    let token = localStorage.getItem('token');
    this.viewPlansService.getAllPlans(token).subscribe({
      next: (data)=>{
        this.plan = data;
      },
      error: (err)=>{}
    });
  }

}
