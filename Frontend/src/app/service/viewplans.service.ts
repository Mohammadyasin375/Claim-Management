import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Plan } from '../model/plan.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ViewplansService {

  constructor(private authService: AuthService,private http:HttpClient) { }

  getAllPlans(token: string): Observable<Plan[]> {
    let header = {
      'Authorization' : 'Basic ' + token
    }
     return this.http.get<Plan[]>( environment.serverUrl +'/plan/all', {headers: header});
  }
}
