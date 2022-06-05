import React from 'react';
import {ButtonLabel, Container} from './styles';
import {IButton} from './types';

export const Button = ({children, ...rest}: IButton) => {
  return (
    <Container {...rest}>
      <ButtonLabel>{children}</ButtonLabel>
    </Container>
  );
};
