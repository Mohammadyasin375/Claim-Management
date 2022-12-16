import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Admin } from '../model/admin.model';
import { Claim } from '../model/claim.model';
import { Member } from '../model/member.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getAdminInfo(token: string): Observable<Admin> {
    let header = {
      'Authorization' : 'Basic ' + token
    }
    return this.http.get<Admin>(environment.serverUrl + '/admin/details', {headers: header});
  }

  getAllMembers(token: string):Observable<Member[]>{
    let header = {
      'Authorization' : 'Basic ' + token
    }
    return this.http.get<Member[]>(environment.serverUrl + '/member/all', {headers: header});
  }
  

  public updateStatusA(status:string,claimId:number):Observable<any>{
    // let header = {
    //   'Authorization' : 'Basic ' + token
    // }
    return this.http.post(environment.serverUrl +'/admin/approval/'+status+'/'+claimId,claimId);
  }

  public updateStatusR(status:string,claimId:number):Observable<any>{
    // let header = {
    //   'Authorization' : 'Basic ' + token
    // }
    return this.http.post(environment.serverUrl +'/admin/approval/'+status+'/'+claimId,claimId);
  }

  public getDocId(claimId:number):Observable<number>{
    return this.http.get<number>(environment.serverUrl + '/claim/getDocId/'+claimId);
  }

  public downloadFile(docId:number){
    return this.http.get(environment.serverUrl + '/claim/document/'+docId,{observe:'response',responseType:'blob'});
  }

  public getClaimById(claimId:number){
    return this.http.get<Claim>(environment.serverUrl + '/claim/getById/'+claimId)

  }
}
