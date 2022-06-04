import React from 'react';
import {NativeModules, Text} from 'react-native';

export const ListDevices = () => {
  const checkBridge = NativeModules.BluetoothConnection.hasBluetoothSupport;
  checkBridge();

  return <Text>List</Text>;
};
