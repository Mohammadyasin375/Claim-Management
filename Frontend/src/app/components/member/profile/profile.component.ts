import { Component, OnInit } from '@angular/core';
import { Member } from 'src/app/model/member.model';
import { AuthService } from 'src/app/service/auth.service';
import { MemberService } from 'src/app/service/member.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private memberService:MemberService,private authService:AuthService) { }

  member:Member;
  ngOnInit(): void {
    let token = localStorage.getItem('token');
    this.memberService.getMemberInfo(token).subscribe({
      next: (data)=>{
        this.member = data;
      },
      error: (err)=>{}
    });
  }

}
