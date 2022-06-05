import React from 'react';
import {
  Border,
  Container,
  DeviceAddress,
  DeviceName,
  Icon,
  InfoContainer,
} from './styles';
import {IScannedDevice} from './types';

export const ScannedDevices = ({deviceName, deviceAddress}: IScannedDevice) => {
  return (
    <Container>
      <Border />

      <Icon
        source={{
          uri: 'https://cdn-icons-png.flaticon.com/512/916/916337.png',
          width: 60,
          height: 60,
        }}
      />

      <InfoContainer>
        <DeviceName>{deviceName}</DeviceName>
        <DeviceAddress>{deviceAddress}</DeviceAddress>
      </InfoContainer>
    </Container>
  );
};
