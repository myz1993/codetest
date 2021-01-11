import React from 'react';
import App from './App';
import axios from 'axios';
import '@testing-library/jest-dom';
import { render, screen } from '@testing-library/react';
import { LABEL_DATA_URL } from '../../config/constants';

jest.mock('axios');
const mockedAxios = axios as jest.Mocked<typeof axios>;

test('renders cypto data list', async () => {
  mockedAxios.get.mockImplementation((url) => {
    if (url === LABEL_DATA_URL) {
      return Promise.resolve({
        data: {
          coins: [
            {
              id: 'tether',
              name: 'Tether',
              symbol: 'USDT',
              market_cap_rank: 3,
              thumb: 'Tether-logo.png',
              large: 'Tether-logo.png',
            },
          ],
        },
      });
    } else {
      return Promise.resolve({
        data: [
          {
            currency: 'tether',
            price: 1.01,
            day: 0.99,
            week: 0,
            volume: 24761899935,
            marketCap: 4140656482,
          },
        ],
      });
    }
  });

  render(<App />);

  const tetherCoin = await screen.findByText('Tether');
  expect(tetherCoin).toBeInTheDocument();
  expect(mockedAxios.get).toHaveBeenCalledTimes(2);
});
