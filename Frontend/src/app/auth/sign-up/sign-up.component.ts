import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Member } from 'src/app/model/member.model';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  signUpForm: FormGroup;
  member: Member;
  msg: string;

  constructor(private authService: AuthService,private router: Router) { }

  ngOnInit(): void {
    this.signUpForm = new FormGroup({
      claimId: new FormControl('', [Validators.required]),
    });
  }


  onSignUp(){
    this.member = {
      memberName: this.signUpForm.value.memberName,
      user: {
        username: this.signUpForm.value.username,
        password: this.signUpForm.value.password
      },
      dob: this.signUpForm.value.dob,
      mobileNo: this.signUpForm.value.mobileNo,
      state: this.signUpForm.value.state,
      city: this.signUpForm.value.city,
      address: this.signUpForm.value.address
    };
    /* password is == repassword */
    let repassword = this.signUpForm.value.repassword;
    if(! (this.signUpForm.value.password == repassword) ){
      this.msg = 'Passwords do not match';
    }
    else{
      this.authService.signup(this.member).subscribe({
        next: (data)=>{
          //naviagate the User to Login
          this.authService.msg$.next('SignUp Success!!')
          this.router.navigateByUrl('/');
        },
        error: (err)=>{
          //display error message
        }
      });
    }
  }

}
