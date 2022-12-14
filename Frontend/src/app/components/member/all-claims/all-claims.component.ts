import { Component, OnInit } from '@angular/core';
import { Claim } from 'src/app/model/claim.model';
import { AllclaimsService } from 'src/app/service/allclaims.service';

@Component({
  selector: 'app-all-claims',
  templateUrl: './all-claims.component.html',
  styleUrls: ['./all-claims.component.css']
})
export class AllClaimsComponent implements OnInit {

  claim:Claim[];

  constructor(private allClaimsService:AllclaimsService) { }

  ngOnInit(): void {
    let token = localStorage.getItem('token');
    this.allClaimsService.getAllClaims(token).subscribe({
      next: (data)=>{
        this.claim = data;
      },
      error: (err)=>{}
    });
  }


}
