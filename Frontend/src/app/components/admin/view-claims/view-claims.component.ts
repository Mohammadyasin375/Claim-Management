import { Component, OnInit } from '@angular/core';
import { Claim } from 'src/app/model/claim.model';
import { ClaimsService } from 'src/app/service/claim.service';

@Component({
  selector: 'app-view-claims',
  templateUrl: './view-claims.component.html',
  styleUrls: ['./view-claims.component.css']
})
export class ViewClaimsComponent implements OnInit {

  constructor(private claimsService:ClaimsService) { }

  claim:Claim[];

  ngOnInit(): void {
    let token = localStorage.getItem('token');
    this.claimsService.getAllClaimsInDb(token).subscribe({
      next: (data)=>{
        this.claim = data;
      },
      error: (err)=>{}
    });
  }

}
