import { Component, OnInit } from '@angular/core';
import { Admin } from 'src/app/model/admin.model';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  admin:Admin;
  constructor(private adminService:AdminService) { }

  ngOnInit(): void {
    let token = localStorage.getItem('token');
    this.adminService.getAdminInfo(token).subscribe({
      next: (data)=>{
        this.admin = data;
      },
      error: (err)=>{}
    });
  }

}
