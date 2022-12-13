import { User } from "./user.model";

export class Member{
  id?: number;
  memberName?: string;
  dob?:string;
  user?: User;
  mobileNo?: number;
  city?: string;
  state?: string;
  address?: string;
}
