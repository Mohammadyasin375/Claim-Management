import { Component, OnInit } from '@angular/core';
import { Member } from 'src/app/model/member.model';
import { AuthService } from 'src/app/service/auth.service';
import { MemberService } from 'src/app/service/member.service';

@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css']
})
export class MemberComponent implements OnInit {

  member:Member;

  constructor(private authService: AuthService,private memberService:MemberService) { }

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
