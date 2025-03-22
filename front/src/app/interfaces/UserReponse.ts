import { Customer } from './Customer';
import { CustomerSupport } from './CustomerSupport';
import { User } from './User';

export interface UserResponse {
  user: User;
  customer: Customer;
  customerSupport: CustomerSupport;
}
