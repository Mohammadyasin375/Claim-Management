import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-apply-claim',
  templateUrl: './apply-claim.component.html',
  styleUrls: ['./apply-claim.component.css']
})
export class ApplyClaimComponent implements OnInit {

  ImagePath: string;
  constructor() { this.ImagePath = '/assets/images/add-claim.jpg'}
  
  ngOnInit(): void {
  }

}
