import { Component, OnInit } from '@angular/core';
import { Claim } from 'src/app/model/claim.model';
import { ClaimsService } from 'src/app/service/claim.service';

@Component({
  selector: 'app-all-claims',
  templateUrl: './all-claims.component.html',
  styleUrls: ['./all-claims.component.css']
})
export class AllClaimsComponent implements OnInit {

  claim:Claim[];

  constructor(private claimsService:ClaimsService) { }

  ngOnInit(): void {
    let token = localStorage.getItem('token');
    this.claimsService.getAllClaims(token).subscribe({
      next: (data)=>{
        this.claim = data;
      },
      error: (err)=>{}
    });
  }


}
