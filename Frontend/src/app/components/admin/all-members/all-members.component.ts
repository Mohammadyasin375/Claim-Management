import { Component, OnInit } from '@angular/core';
import { Member } from 'src/app/model/member.model';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-all-members',
  templateUrl: './all-members.component.html',
  styleUrls: ['./all-members.component.css']
})
export class AllMembersComponent implements OnInit {

  constructor(private adminService:AdminService) { }

  member:Member[];
  ngOnInit(): void {
    let token = localStorage.getItem('token');
    this.adminService.getAllMembers(token).subscribe({
      next: (data)=>{
        this.member = data;
      },
      error: (err)=>{}
    });
  }

}
