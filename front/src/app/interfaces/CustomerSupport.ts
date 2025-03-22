import { User } from './User';
import { Agency } from './Agency';

export interface CustomerSupport {
  customerSupportId: number;
  agency?: Agency;
  userId?: number;
  customerSupport?: User;
}
