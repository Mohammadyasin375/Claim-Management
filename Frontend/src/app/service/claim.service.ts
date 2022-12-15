import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Claim } from '../model/claim.model';
import { Plan } from '../model/plan.model';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ClaimsService {

  constructor(private authService: AuthService,private http:HttpClient) { }
 
  getAllClaims(token: string): Observable<Claim[]> {
    let header = {
      'Authorization' : 'Basic ' + token
    }
     return this.http.get<Claim[]>( environment.serverUrl +'/claim/all', {headers: header});
  }

  claimId:number;
  postClaim(token:string,claim:Claim):Observable<any>{
    let header = {
      'Authorization' : 'Basic ' + token
    }
     return this.http.post<Claim>( environment.serverUrl +'/claim/add/'+claim.planId,claim,{headers: header});
  }


  postDocument(token:string,formData:FormData){
    let header = {
      'Authorization' : 'Basic ' + token
    }
    return this.http.post(environment.serverUrl +'/claim/upload',formData,{headers: header});
  }

  getAllClaimsInDb(token:string):Observable<Claim[]>{
    let header = {
      'Authorization' : 'Basic ' + token
    }
    return this.http.get<Claim[]>(environment.serverUrl +'/claim/getAllFromDb',{headers: header});
  }
}
