import { User } from './User';
import { Agency } from './Agency';

export interface CustomerSupport {
  customerSupportId: number;
  customerSupport: User;
  agency: Agency;
}
