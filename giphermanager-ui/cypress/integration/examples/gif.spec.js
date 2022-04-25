describe("just checking", () => {
  it("just check cypress", () => {
    expect(true).to.equal(true);
  });
});
describe("it should succesfully allow user to login and show up GIF Home page", () => {
  beforeEach(() => {
    cy.visit("/");
  });

  it("check login Functionality", () => {
    cy.get("input[id=login]").type("u103");
    cy.get("#password").type("new123");
    cy.wait(1000);
    cy.get("button[id=submit]").click();
    cy.wait(1000);
    cy.location().should((location) =>
      expect(location.href).to.eq("http://localhost:3000/dashboard")
    );         
    cy.wait(1000);
  });

describe("Check the register functionality", () => {
  beforeEach(() => {
    cy.visit("/");
  });
  it("check register Functionality", () => {    
    cy.get(".registerBtn").click();
    cy.wait(1000);
    cy.location().should((location) =>
      expect(location.href).to.eq("http://localhost:3000/register")
    );      
    cy.wait(1000);
    cy.get("#userId");
    cy.get("#firstname");
    cy.get("#lastname");
    cy.get("#email");
    cy.get("#password");
    cy.get("#cpassword");
    cy.get("button[id=register]").click({ force: true, multiple: true });    
    cy.wait(2000);
    cy.location().should((location) =>
          expect(location.href).to.eq("http://localhost:3000/register")
        );   
  });
});
});

describe('check the form validation of a field in register section', () => {
  beforeEach(() => {
    cy.visit("/");
  });
  it("check register Functionality", () => {    
    cy.get(".registerBtn").click();
    cy.wait(1000);
    cy.location().should((location) =>
      expect(location.href).to.eq("http://localhost:3000/register")
    );      
    cy.wait(1000);
    cy.get('.gif-reg-formholder').within(() => {
      cy.get('#userId').then($el => $el[0].checkValidity()).should('be.true')
      cy.get('#userId').type('Purveee')
        .then($el => $el[0].checkValidity()).should('be.true')
    })
  })
})

describe("Check the Dashboard", () => {
  beforeEach(() => {
    cy.visit("/");
  });
  it("check login Functionality", () => {
    cy.get("input[id=login]").type("u103");
    cy.get("#password").type("new123");
    cy.wait(1000);
    cy.get("button[id=submit]").click();
    cy.wait(1000);
    cy.location().should((location) =>
      expect(location.href).to.eq("http://localhost:3000/dashboard")
    );         
    cy.wait(1000);    
    cy.get(".h-80");
    cy.get("nav").contains("Welcome to the GIFs Library");
    cy.get(".container");
    cy.get(".form-inline");
    cy.get(".form-control").type("Tuesday");
    cy.wait(1000);
    cy.get("button[id=submit]").click({ force: true, multiple: true });    
    cy.wait(2000);
  });
});

describe("Check the On click functionality of Bookmarked gif tab ", () => {
  beforeEach(() => {
    cy.visit("/");
  });
  it("click the favourites tab in the navbar", () => {
    cy.get("input[id=login]").type("u103");
    cy.get("#password").type("new123");
    cy.wait(1000);
    cy.get("button[id=submit]").click();
    cy.wait(1000);
    cy.location().should((location) =>
      expect(location.href).to.eq("http://localhost:3000/dashboard")
    );         
    cy.wait(1000);    
    cy.get("header");
    cy.get(".navbar");
    cy.get(".me-auto");
    cy.get("a").invoke('attr', 'href')
    .then(href => {
      cy.visit("http://localhost:3000/favourites");
    });    
  });
});

describe("Check the functionality of add to Bookmark button", () => {
  beforeEach(() => {
    cy.visit("/");
  });
  it("checks the add to bookmark button functionality", () => {
    cy.get("input[id=login]").type("u103");
    cy.get("#password").type("new123");
    cy.wait(1000);
    cy.get("button[id=submit]").click();
    cy.wait(1000);
    cy.location().should((location) =>
      expect(location.href).to.eq("http://localhost:3000/dashboard")
    );         
    cy.wait(1000);    
    cy.get(".container");
    cy.get(".row");
    cy.get(".gifList");
    cy.get("button[id=add-button]").click({ force: true, multiple: true });
    cy.on('window:alert', (str) => {
      expect(str).contains('Bookmarked')
    }) 
  });
});

describe("Check the On click functionality of favourites tab ", () => {
  beforeEach(() => {
    cy.visit("/");
  });
  it("click the Bookmarked Gifs tab in the navbar", () => {
    cy.get("input[id=login]").type("u103");
    cy.get("#password").type("new123");
    cy.wait(1000);
    cy.get("button[id=submit]").click();
    cy.wait(1000);
    cy.location().should((location) =>
      expect(location.href).to.eq("http://localhost:3000/dashboard")
    );         
    cy.wait(1000);    
    cy.get("header");
    cy.get(".navbar");
    cy.get(".me-auto");
    cy.get("a").invoke('attr', 'href')
    .then(href => {
      cy.visit("http://localhost:3000/favourites");
    });    
  });
});

describe("Check the functionality of signout button", () => {
  beforeEach(() => {
    cy.visit("/");
  });
  it("checks the signout functionality", () => {
    cy.get("input[id=login]").type("u103");
    cy.get("#password").type("new123");
    cy.wait(1000);
    cy.get("button[id=submit]").click();
    cy.wait(1000);
    cy.location().should((location) =>
      expect(location.href).to.eq("http://localhost:3000/dashboard")
    );         
    cy.wait(1000);    
    cy.get("header");
    cy.get(".navbar");
    cy.get(".sign-out").click({ force: true, multiple: true });
    cy.location().should((location) =>
    expect(location.href).to.eq("http://localhost:3000/")
  ); 
  });
});

describe("Check the functionality of delete from Bookmark button", () => {
  beforeEach(() => {
    cy.visit("/");
  });
  it("checks the add to bookmark button functionality", () => {
    cy.get("input[id=login]").type("u103");
    cy.get("#password").type("new123");
    cy.wait(1000);
    cy.get("button[id=submit]").click();
    cy.wait(1000);
    cy.location().should((location) =>
      expect(location.href).to.eq("http://localhost:3000/dashboard")
    );         
    cy.wait(1000);    
    cy.get("header");
    cy.get(".navbar");
    cy.get(".me-auto");
    cy.get("a").invoke('attr', 'href')
    .then(href => {
      cy.visit("http://localhost:3000/favourites");
    });
    cy.wait(1000);    
    cy.get(".container");
    cy.get(".row");
    cy.get(".gifList");
    cy.get("button[id=delete]").click({ force: true, multiple: true });
    cy.on('window:alert', (str) => {
      expect(str).contains('Deleted')
    });  
  });
});
