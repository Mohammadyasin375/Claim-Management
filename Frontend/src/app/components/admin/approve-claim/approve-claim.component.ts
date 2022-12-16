import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Claim } from 'src/app/model/claim.model';
import { AdminService } from 'src/app/service/admin.service';
import { AuthService } from 'src/app/service/auth.service';
import { ClaimsService } from 'src/app/service/claim.service';

@Component({
  selector: 'app-approve-claim',
  templateUrl: './approve-claim.component.html',
  styleUrls: ['./approve-claim.component.css']
})
export class ApproveClaimComponent implements OnInit {

  applyClaimForm: FormGroup;
  msg: string;
  file:any;

  st:string[];
  claimId:number;
  docId:number;
  claim:Claim;
  status1:string='APPROVED';
  status2:string='REJECTED';
  constructor(private adminService:AdminService,private claimService:ClaimsService,private authService: AuthService) { }

  public updateButtonA(){
    // let token = localStorage.getItem('token');
    this.adminService.updateStatusA(this.status1,this.claimId).subscribe({
      next: (data)=>{
        this.authService.msg$.next('Claim Processed sucessfully!')
      },
      error: (err)=>{
      }
    });
  }

  public updateButtonR(){
    this.adminService.updateStatusR(this.status2,this.claimId).subscribe({
      next: (data)=>{
        this.authService.msg$.next('Claim Processed sucessfully!')
      },
      error: (err)=>{
      }
    });
  }
  public downloadFile():void{
    this.adminService.getDocId(this.claimId).subscribe(
      data=>{
        this.docId = data;
        }
    );
    this.adminService.downloadFile(this.docId).subscribe(response=>{
      
      let fileName=response.headers.get('content-disposition')?.split(';')[1].split('=')[1];
      let blob:Blob = response.body as Blob;
      let a = document.createElement('a');
      a.download=fileName;
      a.href=window.URL.createObjectURL(blob);
      a.click();
    })


  }

  ngOnInit(): void {
    this.st=['APPROVED','REJECTED'];
  }

    searchSubmit(searchClaim: NgForm){
      this.claimId= searchClaim.value.claimId;
      this.adminService.getClaimById(this.claimId).subscribe(data=>{
      this.claim = data;
      });
    }

}
