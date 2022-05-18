# -*- encoding: utf-8 -*-
# stub: spinach 0.10.1 ruby lib

Gem::Specification.new do |s|
  s.name = "spinach".freeze
  s.version = "0.10.1"

  s.required_rubygems_version = Gem::Requirement.new(">= 0".freeze) if s.respond_to? :required_rubygems_version=
  s.require_paths = ["lib".freeze]
  s.authors = ["Josep Jaume Rey".freeze, "Josep M. Bach".freeze, "Oriol Gual".freeze, "Marc Divins Castellvi".freeze]
  s.date = "2018-02-21"
  s.description = "Spinach is a BDD framework on top of gherkin".freeze
  s.email = ["info@codegram.com".freeze, "josep.m.bach@gmail.com".freeze, "oriolgual@gmail.com".freeze, "josepjaume@gmail.com".freeze, "marcdivc@gmail.com".freeze]
  s.executables = ["spinach".freeze]
  s.files = ["bin/spinach".freeze]
  s.homepage = "http://github.com/codegram/spinach".freeze
  s.licenses = ["MIT".freeze]
  s.rubygems_version = "3.0.3.1".freeze
  s.summary = "Spinach is a BDD framework on top of gherkin".freeze

  s.installed_by_version = "3.0.3.1" if s.respond_to? :installed_by_version

  if s.respond_to? :specification_version then
    s.specification_version = 4

    if Gem::Version.new(Gem::VERSION) >= Gem::Version.new('1.2.0') then
      s.add_runtime_dependency(%q<gherkin-ruby>.freeze, [">= 0.3.2"])
      s.add_runtime_dependency(%q<colorize>.freeze, [">= 0"])
      s.add_runtime_dependency(%q<json>.freeze, [">= 0"])
      s.add_development_dependency(%q<rake>.freeze, [">= 0"])
      s.add_development_dependency(%q<mocha>.freeze, ["~> 1.0"])
      s.add_development_dependency(%q<sinatra>.freeze, [">= 0"])
      s.add_development_dependency(%q<capybara>.freeze, [">= 0"])
      s.add_development_dependency(%q<pry>.freeze, [">= 0"])
      s.add_development_dependency(%q<simplecov>.freeze, [">= 0"])
      s.add_development_dependency(%q<rspec>.freeze, [">= 0"])
      s.add_development_dependency(%q<minitest>.freeze, ["< 5.0"])
      s.add_development_dependency(%q<fakefs>.freeze, [">= 0.5.2"])
    else
      s.add_dependency(%q<gherkin-ruby>.freeze, [">= 0.3.2"])
      s.add_dependency(%q<colorize>.freeze, [">= 0"])
      s.add_dependency(%q<json>.freeze, [">= 0"])
      s.add_dependency(%q<rake>.freeze, [">= 0"])
      s.add_dependency(%q<mocha>.freeze, ["~> 1.0"])
      s.add_dependency(%q<sinatra>.freeze, [">= 0"])
      s.add_dependency(%q<capybara>.freeze, [">= 0"])
      s.add_dependency(%q<pry>.freeze, [">= 0"])
      s.add_dependency(%q<simplecov>.freeze, [">= 0"])
      s.add_dependency(%q<rspec>.freeze, [">= 0"])
      s.add_dependency(%q<minitest>.freeze, ["< 5.0"])
      s.add_dependency(%q<fakefs>.freeze, [">= 0.5.2"])
    end
  else
    s.add_dependency(%q<gherkin-ruby>.freeze, [">= 0.3.2"])
    s.add_dependency(%q<colorize>.freeze, [">= 0"])
    s.add_dependency(%q<json>.freeze, [">= 0"])
    s.add_dependency(%q<rake>.freeze, [">= 0"])
    s.add_dependency(%q<mocha>.freeze, ["~> 1.0"])
    s.add_dependency(%q<sinatra>.freeze, [">= 0"])
    s.add_dependency(%q<capybara>.freeze, [">= 0"])
    s.add_dependency(%q<pry>.freeze, [">= 0"])
    s.add_dependency(%q<simplecov>.freeze, [">= 0"])
    s.add_dependency(%q<rspec>.freeze, [">= 0"])
    s.add_dependency(%q<minitest>.freeze, ["< 5.0"])
    s.add_dependency(%q<fakefs>.freeze, [">= 0.5.2"])
  end
end
