import { User } from './User';

export interface ChatRequest {
  conversationId: number;
  user: User;
  message: string;
}
