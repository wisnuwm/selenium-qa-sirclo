Feature: Feature sirclo

  Scenario: GET main page
    Given Go to main page
    Then Showing welcome page

  Scenario: Showing form login if user not yet login
    When Go to login page "/login"
    Then Showing form login

  Scenario: Login with valid account will redirect to 3xx
    Given Go to login page "/login"
    When Input username "root"
    And Input password "root123"
    And Click login page
    Then Success login and redirect to 3xx

  Scenario: Login with invalid account will get response 4xx
    Given Go to login page "/login"
    When Input username "invalidUsername"
    And Input password "invalidPass"
    And Click login page
    Then Failed login and redirect to 4xx

  Scenario: Go to login page again after success login will redirect to 3xx
    Given Go to login page "/login"
    And Input username "root"
    And Input password "root123"
    And Click login page
    And Success login and redirect to 3xx
    When Go to login page "/login"
    Then Will redirect to "/"

  Scenario: Logout after login
    Given Go to login page "/login"
    And Input username "root"
    And Input password "root123"
    And Click login page
    And Success login and redirect to 3xx
    When Go to logout page "/logout"
    Then Success logout, cookies session id removed and redirect to 3xx

  Scenario: GET data before login will redirect to login page
    When Go to data page "/data"
    Then Redirect to login page

  Scenario: GET data after login will showing 2 table
    Given Go to login page "/login"
    And Input username "root"
    And Input password "root123"
    And Click login page
    When Go to data page "/data"
    Then Showing 2 table contains 10 pemasukan and pengeluaran terakhir

  Scenario: Filter data by timestamp start and end
    Given Go to login page "/login"
    And Input username "root"
    And Input password "root123"
    And Click login page
    And Go to data page "/data"
    When Filter data by timestamp start and end
    Then Will showing data related by filter timestamp

  Scenario: Filter data by timestamp start > end
    Given Go to login page "/login"
    And Input username "root"
    And Input password "root123"
    And Click login page
    And Go to data page "/data"
    When Filter data by timestamp start more than end
    Then Will redirect to 4xx

  Scenario: Input data pemasukan & keluaran, timestamp, deskripsi and jumlah
    Given Go to login page "/login"
    And Input username "root"
    And Input password "root123"
    And Click login page
    And Go to data page "/data"
    When Input pemasukan baru with value "10000"
    And Input pengeluaran baru with value "5000"
    And Input timestamp
    And Input description with value "description"
    And Input jumlah "1"
    When Click button confirm
    Then Success input data

  Scenario Outline: Go to other page which is not registered page will get response 404 not found
    When Go to page "<pages>"
    Then Will showing page not found
    Examples:
    |pages|
    |/xyz  |
    |/abc  |
    |/aaa  |
    |/bbb  |