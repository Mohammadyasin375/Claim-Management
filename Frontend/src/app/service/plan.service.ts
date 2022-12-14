import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Plan } from '../model/plan.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AddplanService {

  constructor(private authService: AuthService,private http:HttpClient) { }

  addPlan(token: string,plan:Plan): Observable<any>{
    let header = {
      'Authorization' : 'Basic ' + token
    }
     return this.http.post( environment.serverUrl +'/plan/add', plan,{headers: header});
  }

}
