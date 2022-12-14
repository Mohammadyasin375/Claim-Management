import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Claim } from 'src/app/model/claim.model';
import { AuthService } from 'src/app/service/auth.service';
import { ClaimsService } from 'src/app/service/claim.service';

@Component({
  selector: 'app-apply-claim',
  templateUrl: './apply-claim.component.html',
  styleUrls: ['./apply-claim.component.css']
})
export class ApplyClaimComponent implements OnInit {

  claim:Claim;
  applyClaimForm: FormGroup;
  msg: string;
  file:any;

  constructor(private claimService:ClaimsService,private authService: AuthService) {}

  getFile(event:any){
    this.file=event.target.files[0];
    console.log("file",this.file);
  }
  
  ngOnInit(): void {
    // this.claimService.getAllManagers().subscribe({
    //   next: (data)=>{
    //     this.managerArry = data;
    //   },
    //   error: ()=>{}
    // });

    this.applyClaimForm = new FormGroup({
      planId: new FormControl('', [Validators.required]),
      claimAmount: new FormControl('', [Validators.required]),
      admitDate: new FormControl('', [Validators.required]),
      dischargeDate: new FormControl('', [Validators.required]),
      hospitalName: new FormControl('', [Validators.required]),
    });

  }

  onSubmitClaim(){

    this.claim = {
      planId: this.applyClaimForm.value.planId,
      claimAmount: this.applyClaimForm.value.claimAmount,
      admitDate: this.applyClaimForm.value.admitDate,
      dischargeDate: this.applyClaimForm.value.dischargeDate,
      hospitalName: this.applyClaimForm.value.hospitalName
    };

    let token = localStorage.getItem('token');
    this.claimService.postClaim(token,this.claim).subscribe({
      next: (data)=>{
        this.authService.msg$.next('Plan Submitted!!')
      },
      error: (err)=>{
        
      }
    });

    let formData = new FormData();
    formData.set('file',this.file);

    this.claimService.postDocument(formData).subscribe({
      next: (data)=>{
        this.authService.msg$.next('Claim Submitted!!')
      },
      error: (err)=>{
        
      }
    });

  }

}
